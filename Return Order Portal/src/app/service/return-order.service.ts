import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, exhaustMap, of, Subject, take, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { confirmResponseData, ReturnRequest } from '../return-orders/ReturnRequest.model';
import { AuthService } from './auth.service';


export interface ReturnResponseData {
  requestId: string;
  processingCharge: number;
  packageAndDeliveryCharge: number;
  dateOfDelivery: Date;
}

export interface ReturnConfirmation extends ReturnResponseData {
  componentType: string,
  componentName: string,
  quantiy: number
}



@Injectable({
  providedIn: 'root'
})
export class ReturnOrderService {

  private sentReturnOrderURL = environment.sentReturnOrderURL;
  private confirmReturnURL: String = environment.confirmReturn;
  constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }
  private returnConfirmation: ReturnConfirmation;
  returnConfirmationChanged = new Subject<ReturnConfirmation>();

  placeReturn(returnReq: ReturnRequest) {
//returnReq.defectiveComponentDetail.componentType="sa";
    return this.http
      .post<ReturnResponseData>(
        this.sentReturnOrderURL,
        returnReq

      )
      .pipe(
        catchError(this.handleError),
        tap(resData => {
        })
      );
  }

  setReturnConfirmation(returnConfirmation: ReturnConfirmation) {
    this.returnConfirmation = returnConfirmation;
    this.returnConfirmationChanged.next(this.returnConfirmation);
  }
  getReturnConfirmation() {
    return this.returnConfirmation;
  }

  confirmReturn(reqId: string, charge: number) {
    let card;
    let limit;
this.authService.user.pipe(
  take(1) ).subscribe(u=>{
    card=u.creditCardNum;
    limit=u.creditLimit;
  }).unsubscribe();


    return this.http
      .post<confirmResponseData>(
        this.confirmReturnURL + reqId + "/" + card + "/" + limit + "/" + charge, null,
      )
      .pipe(
        catchError(this.handleError),
        tap(resData => {
        })
      );
  }




  private handleError(errorRes: HttpErrorResponse) {

    let errorMessage = 'An unknown error occurred!';
    if (!errorRes.error || !errorRes.error.message) {
      return throwError(errorMessage);
    }
    if(errorRes.status===500 || errorRes.status===503){
      errorMessage="Sorry server went please try again after some time!!!"
    }
    switch (errorRes.error.message) {
    
      case "Invalid Token": {
        alert("Session is expired please login again");
        errorMessage = errorRes.error.message;
        break;
      }
      case "INVALID_COMPONENT_TYPE":{
        errorMessage = "Please select valid component type";
        break;
      }
      case "RETURN ORDER NOT FOUND": {
        errorMessage = errorRes.error.message;
        break;
      }
      case "INVALID_QUANTITY":{
        errorMessage = errorRes.error.message;
        break;
      }
    
      case "VALIDATION FAILED":{
        errorMessage = errorRes.error.message;
        break;
      }
    }
    return throwError(errorMessage);
  }
}
