export class DefectiveComponentDetail {
  constructor(public componentName: string, public componentType: string, public quantity: number) {

  }
}

export class ReturnRequest {
  constructor(public name: string, public contactNumber: number, public defectiveComponentDetail: DefectiveComponentDetail) { }
}

export class ConfirmPayment {
  constructor(public requestId: number, public creditCardNumber: string, public creditLimit: number, public processingCharge: number) {

  }
}

export interface ResponseData {
  requestId: number,
  processingCharge: number,
  packagingAndDeliveryCharge: number,
  dateOfDelivery: string
}

export interface confirmResponseData{
  isConfirmed:boolean
}