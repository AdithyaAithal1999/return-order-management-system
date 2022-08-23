import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, catchError, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../shared/User.model';



export interface AuthResponseData {
  userNAme: string;
  token: string;
  expiresIn: string;
  userId:string;
  creditCardNum:string;
  creditLimit:number;

}


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  user = new BehaviorSubject<User>(null);
  private tokenExpirationTimer: any;
  loginURL:string;
  //firebaseLoginURL:string="https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyDids4ysmw-5ENyjatRzBTc2wlBNEUUmjM"
  constructor(private http: HttpClient,private router: Router) {}

  login(email: string, password: string) {
    
   this.loginURL= environment.loginURL;
    return this.http
      .post<AuthResponseData>(
        this.loginURL,
        {
          email: email,
          password: password,
         // returnSecureToken: true
        }
      )
      .pipe(
        catchError(this.handleError),
        tap(resData => {
          
          this.handleAuthentication(
            resData.userNAme,
            resData.userId,
            resData.token,
            +resData.expiresIn,
            resData.creditCardNum,
            resData.creditLimit
          );
        })
      );
  }



  autoLogin() {
    
    const userData: {
      email: string;
      id: string;
      _token: string;
      _tokenExpirationDate: string;
       creditCardNum:string;
       creditLimit:number;
    } = JSON.parse(localStorage.getItem('userData'));
    
    if (!userData) {
      
      return;
    }

    const loadedUser = new User(
      userData.email,
      userData.id,
      userData._token,
      new Date(userData._tokenExpirationDate),
      userData.creditCardNum,
      userData.creditLimit
    );

    if (loadedUser.token) {
      
      this.user.next(loadedUser);
      const expirationDuration =
        new Date(userData._tokenExpirationDate).getTime() -
        new Date().getTime();
      this.autoLogout(expirationDuration);
    }
  }

  logout() {
    this.user.next(null);
    this.router.navigate(['/auth']);
    localStorage.removeItem('userData');
    if (this.tokenExpirationTimer) {
      clearTimeout(this.tokenExpirationTimer);
    }
    this.tokenExpirationTimer = null;
  }

  autoLogout(expirationDuration: number) {
    
    this.tokenExpirationTimer = setTimeout(() => {
     
      alert("Your Seession got expired");
      this.logout();
    }, expirationDuration);
  }

  private handleAuthentication(
    email: string,
    userId: string,
    token: string,
    expiresIn: number,
    creditCardNum:string,
    creditLimit:number
  ) {
    const expirationDate = new Date(new Date().getTime() + expiresIn );
    const user = new User(email, userId, token, expirationDate, creditCardNum,creditLimit);

    this.user.next(user);
    
    
    this.autoLogout(expiresIn );
    localStorage.setItem('userData', JSON.stringify(user));

  }

  private handleError(errorRes: HttpErrorResponse) {
    
    let errorMessage = 'An unknown error occurred!';
    if (!errorRes.error || !errorRes.error.message) {
      return throwError(errorMessage);
    }
    if(errorRes.status===500){
      errorMessage="Sorry server went please try again after some time!!!"
    }
    switch (errorRes.error.message) {
      case 'Bad credentials':{
       
        errorMessage = 'Invalid Login credentials. Please trt again';
        break;
      }
      case 'INVALID_CREDENTIALS':{
       
        errorMessage = 'Invalid Login credentials. Please trt again';
        break;
      }
    }
    return throwError(errorMessage);
  }
}
