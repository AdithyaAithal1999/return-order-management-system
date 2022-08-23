import { Injectable } from '@angular/core';
import { ResponseData, ReturnRequest } from '../return-orders/ReturnRequest.model';
import { ReturnResponseData } from '../service/return-order.service';

@Injectable({
  providedIn: 'root'
})
export class DataStorageService {

  private placeReturn:ReturnRequest=null;
  private returnResponse:ReturnResponseData=null;
  constructor() { }

  getPlaceReturn(){
    return this.placeReturn;
  }

  getReturnResponse(){
    return this.returnResponse;
  }

  
  setPlaceReturn(req:ReturnRequest){
     this.placeReturn=req;
  }

  setReturnResponse(res:ReturnResponseData){
     this.returnResponse=res;
  }
}
