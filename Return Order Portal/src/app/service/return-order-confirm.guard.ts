import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { ReturnOrderService } from './return-order.service';

@Injectable({
  providedIn: 'root'
})
export class ReturnOrderConfirmGuard implements CanActivate {
  constructor(private router:Router,private returnOrderService:ReturnOrderService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    
      if(this.returnOrderService.getReturnConfirmation())
      return true;
      return this.router.createUrlTree(['/home'])
  }
  
}
