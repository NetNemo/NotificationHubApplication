import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SystemEvent } from 'app/shared/model/system-event.model';
import { SystemEventService } from './system-event.service';
import { SystemEventComponent } from './system-event.component';
import { SystemEventDetailComponent } from './system-event-detail.component';
import { SystemEventUpdateComponent } from './system-event-update.component';
import { SystemEventDeletePopupComponent } from './system-event-delete-dialog.component';
import { ISystemEvent } from 'app/shared/model/system-event.model';

@Injectable({ providedIn: 'root' })
export class SystemEventResolve implements Resolve<ISystemEvent> {
    constructor(private service: SystemEventService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((systemEvent: HttpResponse<SystemEvent>) => systemEvent.body));
        }
        return of(new SystemEvent());
    }
}

export const systemEventRoute: Routes = [
    {
        path: 'system-event',
        component: SystemEventComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.systemEvent.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'system-event/:id/view',
        component: SystemEventDetailComponent,
        resolve: {
            systemEvent: SystemEventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.systemEvent.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'system-event/new',
        component: SystemEventUpdateComponent,
        resolve: {
            systemEvent: SystemEventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.systemEvent.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'system-event/:id/edit',
        component: SystemEventUpdateComponent,
        resolve: {
            systemEvent: SystemEventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.systemEvent.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const systemEventPopupRoute: Routes = [
    {
        path: 'system-event/:id/delete',
        component: SystemEventDeletePopupComponent,
        resolve: {
            systemEvent: SystemEventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'notificationHubApp.systemEvent.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
