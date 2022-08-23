import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { exhaustMap, map, Observable, take, tap } from 'rxjs';
import { AuthService } from '../service/auth.service';
import {  ReturnResponseData, ReturnOrderService } from '../service/return-order.service';
import { DataStorageService } from '../shared/data-storage.service';
import { DefectiveComponentDetail, ReturnRequest } from './ReturnRequest.model';

@Component({
  selector: 'app-return-orders',
  templateUrl: './return-orders.component.html',
  styleUrls: ['./return-orders.component.css']
})
export class ReturnOrdersComponent implements OnInit {

  returnForm: FormGroup;
  isLoading:boolean=true;
  errorMessage:string='';
  
  constructor(private authService:AuthService,private returnService:ReturnOrderService,private router:Router,private dataService:DataStorageService) { }

  ngOnInit(): void {
    this.initForm();
    this.isLoading=false;
    this.returnForm.valueChanges.subscribe(x => {
    // this.getFormValidationErrors();
  })
  }
onSubmit(){
  // this.authService.user.pipe(
  //   take(1),
  //   tap(user=>{
  //     user.id;
  //   })
  //   )
//console.log(this.returnForm.value);
//this.returnForm.value.componentName
this.isLoading=true;
let returnObs: Observable<ReturnResponseData>;
let componentDetail=new DefectiveComponentDetail(this.returnForm.value["componentName"],this.returnForm.value.componentType,this.returnForm.value["quantity"]);
let placeReturn=new ReturnRequest(this.returnForm.value.name,this.returnForm.value.contactNumber,componentDetail)
returnObs=this.returnService.placeReturn(placeReturn);
returnObs.subscribe(
  (resData) => {
    
    this.returnService.setReturnConfirmation({quantiy:componentDetail.quantity,componentType:componentDetail.componentType,componentName:componentDetail.componentName,dateOfDelivery:resData.dateOfDelivery,requestId:resData.requestId,packageAndDeliveryCharge:resData.packageAndDeliveryCharge,processingCharge:resData.processingCharge})
    
    //this.dataService.setReturnResponse(resData);
    this.isLoading = false;
    this.router.navigate(['/returnConfirm']);//,{state:{req:placeReturn,res:resData}}
  },
  (errorMessage) => {
    
    this.errorMessage = errorMessage;
   // this.showAlert(errorMessage);
    this.isLoading = false;
    if(errorMessage=="Invalid Token")
    this.router.navigate(["/auth"]);

  }
);
}

private initForm(){
this.returnForm = new FormGroup({
  'name':new FormControl(null,[Validators.required,Validators.minLength(3),Validators.maxLength(30)]),
  'contactNumber': new FormControl(null, [Validators.pattern('^(\\+91[\-\\s]?)?[0]?(91)?[789]\\d{9}$'), Validators.minLength(10), Validators.maxLength(10), Validators.required]),//, this.noWhitespaceValidator
      'componentName': new FormControl(null, [Validators.required, Validators.maxLength(30)]),//, this.noWhitespaceValidator
      'componentType': new FormControl('Integral', Validators.required),
      'quantity': new FormControl(null, [Validators.required, Validators.max(9999), Validators.min(1)])  
});
}
get returnFormControls(): any {
  return this.returnForm['controls'];
}
resetForm(){
  this.isLoading=true;
  this.returnForm.reset(  );
  this.initForm();
      this.errorMessage = '';
      this.isLoading=false;
   // this.getFormValidationErrors();
}


// getFormValidationErrors() {
//   Object.keys(this.returnForm.controls).forEach(key => {
//     const controlErrors = this.returnForm.get(key).errors;
//     if (controlErrors != null) {
//       Object.keys(controlErrors).forEach(keyError => {
//        console.log('Key control: ' + key + ', keyError: ' + keyError + ', err value: ', controlErrors[keyError]);
//       });
//     }
//   });
// }
}
