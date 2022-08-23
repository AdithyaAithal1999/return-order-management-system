import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { AuthComponent } from './auth/auth.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoadingSpinnerComponent } from './shared/loading-spinner/loading-spinner.component';
import { AlertComponent } from './alert/alert.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { ReturnOrdersComponent } from './return-orders/return-orders.component';
import { AuthInterceptorService } from './service/auth-interceptor.service';
import { ReturnConfirmComponent } from './return-confirm/return-confirm.component';
import { HomepageComponent } from './homepage/homepage.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AuthComponent,
    LoadingSpinnerComponent,
    AlertComponent,
    ReturnOrdersComponent,
    ReturnConfirmComponent,
    HomepageComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [{provide:HTTP_INTERCEPTORS,useClass:AuthInterceptorService,multi:true}],
  bootstrap: [AppComponent],
  entryComponents:[AlertComponent]
})
export class AppModule { }
