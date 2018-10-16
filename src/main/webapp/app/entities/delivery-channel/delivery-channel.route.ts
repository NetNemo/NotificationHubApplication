import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DeliveryChannel } from 'app/shared/model/delivery-channel.model';
import { DeliveryChannelService } from './delivery-channel.service';
import { DeliveryChannelComponent } from './delivery-channel.component';
import { DeliveryChannelDetailComponent } from './delivery-channel-detail.component';
import { DeliveryChannelUpdateComponent } from './delivery-channel-update.component';
import { DeliveryChannelDeletePopupComponent } from './delivery-channel-delete-dialog.component';
import { IDeliveryChannel } from 'app/shared/model/delivery-channel.model';

@Injectable({ providedIn: 'root' })
export class DeliveryChannelResolve implements Resolve<IDeliveryChannel> {
    constructor(private service: DeliveryChannelService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((deliveryChannel: HttpResponse<DeliveryChannel>) => deliveryChannel.body));
        }
        return of(new DeliveryChannel());
    }
}

export const deliveryChannelRoute: Routes = [
    {
        path: 'delivery-channel',
        component: DeliveryChannelComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.deliveryChannel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'delivery-channel/:id/view',
        component: DeliveryChannelDetailComponent,
        resolve: {
            deliveryChannel: DeliveryChannelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.deliveryChannel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'delivery-channel/new',
        component: DeliveryChannelUpdateComponent,
        resolve: {
            deliveryChannel: DeliveryChannelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.deliveryChannel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'delivery-channel/:id/edit',
        component: DeliveryChannelUpdateComponent,
        resolve: {
            deliveryChannel: DeliveryChannelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.deliveryChannel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const deliveryChannelPopupRoute: Routes = [
    {
        path: 'delivery-channel/:id/delete',
        component: DeliveryChannelDeletePopupComponent,
        resolve: {
            deliveryChannel: DeliveryChannelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.deliveryChannel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
