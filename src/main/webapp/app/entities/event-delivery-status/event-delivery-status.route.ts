import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EventDeliveryStatus } from 'app/shared/model/event-delivery-status.model';
import { EventDeliveryStatusService } from './event-delivery-status.service';
import { EventDeliveryStatusComponent } from './event-delivery-status.component';
import { EventDeliveryStatusDetailComponent } from './event-delivery-status-detail.component';
import { EventDeliveryStatusUpdateComponent } from './event-delivery-status-update.component';
import { EventDeliveryStatusDeletePopupComponent } from './event-delivery-status-delete-dialog.component';
import { IEventDeliveryStatus } from 'app/shared/model/event-delivery-status.model';

@Injectable({ providedIn: 'root' })
export class EventDeliveryStatusResolve implements Resolve<IEventDeliveryStatus> {
    constructor(private service: EventDeliveryStatusService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((eventDeliveryStatus: HttpResponse<EventDeliveryStatus>) => eventDeliveryStatus.body));
        }
        return of(new EventDeliveryStatus());
    }
}

export const eventDeliveryStatusRoute: Routes = [
    {
        path: 'event-delivery-status',
        component: EventDeliveryStatusComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.eventDeliveryStatus.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'event-delivery-status/:id/view',
        component: EventDeliveryStatusDetailComponent,
        resolve: {
            eventDeliveryStatus: EventDeliveryStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.eventDeliveryStatus.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'event-delivery-status/new',
        component: EventDeliveryStatusUpdateComponent,
        resolve: {
            eventDeliveryStatus: EventDeliveryStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.eventDeliveryStatus.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'event-delivery-status/:id/edit',
        component: EventDeliveryStatusUpdateComponent,
        resolve: {
            eventDeliveryStatus: EventDeliveryStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.eventDeliveryStatus.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const eventDeliveryStatusPopupRoute: Routes = [
    {
        path: 'event-delivery-status/:id/delete',
        component: EventDeliveryStatusDeletePopupComponent,
        resolve: {
            eventDeliveryStatus: EventDeliveryStatusResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.eventDeliveryStatus.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
