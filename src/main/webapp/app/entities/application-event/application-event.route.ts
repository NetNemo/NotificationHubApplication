import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ApplicationEvent } from 'app/shared/model/application-event.model';
import { ApplicationEventService } from './application-event.service';
import { ApplicationEventComponent } from './application-event.component';
import { ApplicationEventDetailComponent } from './application-event-detail.component';
import { ApplicationEventUpdateComponent } from './application-event-update.component';
import { ApplicationEventDeletePopupComponent } from './application-event-delete-dialog.component';
import { IApplicationEvent } from 'app/shared/model/application-event.model';

@Injectable({ providedIn: 'root' })
export class ApplicationEventResolve implements Resolve<IApplicationEvent> {
    constructor(private service: ApplicationEventService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((applicationEvent: HttpResponse<ApplicationEvent>) => applicationEvent.body));
        }
        return of(new ApplicationEvent());
    }
}

export const applicationEventRoute: Routes = [
    {
        path: 'application-event',
        component: ApplicationEventComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.applicationEvent.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'application-event/:id/view',
        component: ApplicationEventDetailComponent,
        resolve: {
            applicationEvent: ApplicationEventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.applicationEvent.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'application-event/new',
        component: ApplicationEventUpdateComponent,
        resolve: {
            applicationEvent: ApplicationEventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.applicationEvent.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'application-event/:id/edit',
        component: ApplicationEventUpdateComponent,
        resolve: {
            applicationEvent: ApplicationEventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.applicationEvent.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const applicationEventPopupRoute: Routes = [
    {
        path: 'application-event/:id/delete',
        component: ApplicationEventDeletePopupComponent,
        resolve: {
            applicationEvent: ApplicationEventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.applicationEvent.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
