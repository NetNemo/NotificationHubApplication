import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NotificationHubSharedModule } from 'app/shared';
import {
    LdapUserComponent,
    LdapUserDetailComponent,
    LdapUserUpdateComponent,
    LdapUserDeletePopupComponent,
    LdapUserDeleteDialogComponent,
    ldapUserRoute,
    ldapUserPopupRoute
} from './';

const ENTITY_STATES = [...ldapUserRoute, ...ldapUserPopupRoute];

@NgModule({
    imports: [NotificationHubSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LdapUserComponent,
        LdapUserDetailComponent,
        LdapUserUpdateComponent,
        LdapUserDeleteDialogComponent,
        LdapUserDeletePopupComponent
    ],
    entryComponents: [LdapUserComponent, LdapUserUpdateComponent, LdapUserDeleteDialogComponent, LdapUserDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationHubLdapUserModule {}
