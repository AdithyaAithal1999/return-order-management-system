import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DefectiveComponentDetail, ReturnRequest } from '../return-orders/ReturnRequest.model';
import { ReturnConfirmation, ReturnOrderService, ReturnResponseData } from '../service/return-order.service';
import { DataStorageService } from '../shared/data-storage.service';

@Component({
  selector: 'app-return-confirm',
  templateUrl: './return-confirm.component.html',
  styleUrls: ['./return-confirm.component.css']
})
export class ReturnConfirmComponent implements OnInit, OnDestroy {

  isLoading = true;
  errorMessage: string;
  constructor(private dataService: DataStorageService, private returnOrderService: ReturnOrderService, private router: Router) { }

  returnConfirmation:ReturnConfirmation;
  successMessage: string = '';
  ngOnInit(): void {

    this.returnConfirmation = this.returnOrderService.getReturnConfirmation();
    this.isLoading = false;

    if (this.returnConfirmation === null|| this.returnConfirmation===undefined )
      this.router.navigate(['/returnorders'])
  }
  ngOnDestroy(): void {
    this.dataService.setPlaceReturn(null);
    this.dataService.setReturnResponse(null);

  }


  onConfirm() {
    this.returnOrderService.confirmReturn(this.returnConfirmation.requestId,this.returnConfirmation.processingCharge+this.returnConfirmation.packageAndDeliveryCharge).subscribe(
      (resData) => {

        this.isLoading = false;
        this.successMessage = "Order Confirmed"

      },
      (errorMessage) => {

        this.errorMessage = errorMessage;
        // this.showAlert(errorMessage);
        this.isLoading = false;


      }
    )
  }

  onLater() {
    this.router.navigate(["/returnorders"]);
  }
}
