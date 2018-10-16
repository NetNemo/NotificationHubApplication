import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LdapUser } from 'app/shared/model/ldap-user.model';
import { LdapUserService } from './ldap-user.service';
import { LdapUserComponent } from './ldap-user.component';
import { LdapUserDetailComponent } from './ldap-user-detail.component';
import { LdapUserUpdateComponent } from './ldap-user-update.component';
import { LdapUserDeletePopupComponent } from './ldap-user-delete-dialog.component';
import { ILdapUser } from 'app/shared/model/ldap-user.model';

@Injectable({ providedIn: 'root' })
export class LdapUserResolve implements Resolve<ILdapUser> {
    constructor(private service: LdapUserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((ldapUser: HttpResponse<LdapUser>) => ldapUser.body));
        }
        return of(new LdapUser());
    }
}

export const ldapUserRoute: Routes = [
    {
        path: 'ldap-user',
        component: LdapUserComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.ldapUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ldap-user/:id/view',
        component: LdapUserDetailComponent,
        resolve: {
            ldapUser: LdapUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.ldapUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ldap-user/new',
        component: LdapUserUpdateComponent,
        resolve: {
            ldapUser: LdapUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.ldapUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ldap-user/:id/edit',
        component: LdapUserUpdateComponent,
        resolve: {
            ldapUser: LdapUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.ldapUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ldapUserPopupRoute: Routes = [
    {
        path: 'ldap-user/:id/delete',
        component: LdapUserDeletePopupComponent,
        resolve: {
            ldapUser: LdapUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.ldapUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
