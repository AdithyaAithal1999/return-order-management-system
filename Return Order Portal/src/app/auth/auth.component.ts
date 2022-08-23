import { Component, ComponentFactoryResolver, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { AlertComponent } from '../alert/alert.component';
import { AuthResponseData, AuthService } from '../service/auth.service';
import { PlaceHolderDirective } from './placeHolder/placeHolder.directive';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  isLoginMode = true;
  isLoading = false;
  error: string = '';
  alertSub: Subscription;
  @ViewChild(PlaceHolderDirective, { static: false })
  alertHost: PlaceHolderDirective;
  constructor(
    private authService: AuthService,
    private router: Router,
    private componentFactoryResolver: ComponentFactoryResolver
  ) {}

  defaultLoginCrediential={userName:"ganesh@gmail.com",password:"ganeshganesh"}
  ngOnInit(): void {}

  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
  }

  onhandleError() {
    this.error = '';
  }
  onSubmit(form: NgForm) {
    if (!form.valid) {
      return;
    }
    
    const email = form.value.email;
    const password = form.value.password;

    let authObs: Observable<AuthResponseData>;

    this.isLoading = true;

    if (this.isLoginMode) {
      this.error = '';
      authObs = this.authService.login(email, password);
    } else {
      //authObs = this.authService.signup(email, password);
    }

    authObs.subscribe(
      (resData) => {
        this.isLoading = false;
        this.router.navigate(['/home']);
      },
      (errorMessage) => {
        this.error = errorMessage;
       // this.showAlert(errorMessage);
        this.isLoading = false;
      }
    );

    form.reset();
  }

  showAlert(message: string) {
    const alertComponentFactory =
      this.componentFactoryResolver.resolveComponentFactory(AlertComponent);
    const hostViewCiontainer = this.alertHost.viewContainerRef;
    hostViewCiontainer.clear();
    const componentRef = hostViewCiontainer.createComponent(
      alertComponentFactory
    );

    componentRef.instance.message = message;

    this.alertSub = componentRef.instance.errorClose.subscribe(() => {
      this.alertSub.unsubscribe();
      hostViewCiontainer.clear();
    });
  }

}
