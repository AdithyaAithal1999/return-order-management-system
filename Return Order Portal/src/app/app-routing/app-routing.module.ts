import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from '../auth/auth.component';
import { ReturnOrdersComponent } from '../return-orders/return-orders.component';
import { AuthGuard } from '../service/auth.guard';
import { ReturnConfirmComponent } from '../return-confirm/return-confirm.component';
import { ReturnOrderConfirmGuard } from '../service/return-order-confirm.guard';
import { HomepageComponent } from '../homepage/homepage.component';
import { PageNotFoundComponent } from '../page-not-found/page-not-found.component';


const appRoutes:Routes=[
  {path:"",redirectTo:"/home",pathMatch:'full'},
  {path:"home",component:HomepageComponent,canActivate:[AuthGuard]},
  {path:"returnorders",component:ReturnOrdersComponent,canActivate:[AuthGuard]},
  {path:"returnConfirm",component:ReturnConfirmComponent,canActivate:[AuthGuard,ReturnOrderConfirmGuard]},
  {path:"auth",component:AuthComponent},
  {path:"**",component:PageNotFoundComponent}
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  exports:[RouterModule]
})
export class AppRoutingModule { }
