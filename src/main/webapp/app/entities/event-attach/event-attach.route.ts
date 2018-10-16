import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EventAttach } from 'app/shared/model/event-attach.model';
import { EventAttachService } from './event-attach.service';
import { EventAttachComponent } from './event-attach.component';
import { EventAttachDetailComponent } from './event-attach-detail.component';
import { EventAttachUpdateComponent } from './event-attach-update.component';
import { EventAttachDeletePopupComponent } from './event-attach-delete-dialog.component';
import { IEventAttach } from 'app/shared/model/event-attach.model';

@Injectable({ providedIn: 'root' })
export class EventAttachResolve implements Resolve<IEventAttach> {
    constructor(private service: EventAttachService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((eventAttach: HttpResponse<EventAttach>) => eventAttach.body));
        }
        return of(new EventAttach());
    }
}

export const eventAttachRoute: Routes = [
    {
        path: 'event-attach',
        component: EventAttachComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.eventAttach.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'event-attach/:id/view',
        component: EventAttachDetailComponent,
        resolve: {
            eventAttach: EventAttachResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.eventAttach.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'event-attach/new',
        component: EventAttachUpdateComponent,
        resolve: {
            eventAttach: EventAttachResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.eventAttach.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'event-attach/:id/edit',
        component: EventAttachUpdateComponent,
        resolve: {
            eventAttach: EventAttachResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.eventAttach.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const eventAttachPopupRoute: Routes = [
    {
        path: 'event-attach/:id/delete',
        component: EventAttachDeletePopupComponent,
        resolve: {
            eventAttach: EventAttachResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.eventAttach.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
