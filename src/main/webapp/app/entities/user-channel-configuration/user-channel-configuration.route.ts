import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { UserChannelConfiguration } from 'app/shared/model/user-channel-configuration.model';
import { UserChannelConfigurationService } from './user-channel-configuration.service';
import { UserChannelConfigurationComponent } from './user-channel-configuration.component';
import { UserChannelConfigurationDetailComponent } from './user-channel-configuration-detail.component';
import { UserChannelConfigurationUpdateComponent } from './user-channel-configuration-update.component';
import { UserChannelConfigurationDeletePopupComponent } from './user-channel-configuration-delete-dialog.component';
import { IUserChannelConfiguration } from 'app/shared/model/user-channel-configuration.model';

@Injectable({ providedIn: 'root' })
export class UserChannelConfigurationResolve implements Resolve<IUserChannelConfiguration> {
    constructor(private service: UserChannelConfigurationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((userChannelConfiguration: HttpResponse<UserChannelConfiguration>) => userChannelConfiguration.body));
        }
        return of(new UserChannelConfiguration());
    }
}

export const userChannelConfigurationRoute: Routes = [
    {
        path: 'user-channel-configuration',
        component: UserChannelConfigurationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.userChannelConfiguration.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-channel-configuration/:id/view',
        component: UserChannelConfigurationDetailComponent,
        resolve: {
            userChannelConfiguration: UserChannelConfigurationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.userChannelConfiguration.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-channel-configuration/new',
        component: UserChannelConfigurationUpdateComponent,
        resolve: {
            userChannelConfiguration: UserChannelConfigurationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.userChannelConfiguration.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-channel-configuration/:id/edit',
        component: UserChannelConfigurationUpdateComponent,
        resolve: {
            userChannelConfiguration: UserChannelConfigurationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.userChannelConfiguration.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userChannelConfigurationPopupRoute: Routes = [
    {
        path: 'user-channel-configuration/:id/delete',
        component: UserChannelConfigurationDeletePopupComponent,
        resolve: {
            userChannelConfiguration: UserChannelConfigurationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.userChannelConfiguration.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
