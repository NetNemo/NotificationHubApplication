import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { NotificationHubLdapUserModule } from './ldap-user/ldap-user.module';
import { NotificationHubEventModule } from './event/event.module';
import { NotificationHubEventAttachModule } from './event-attach/event-attach.module';
import { NotificationHubEventDeliveryStatusModule } from './event-delivery-status/event-delivery-status.module';
import { NotificationHubSystemEventModule } from './system-event/system-event.module';
import { NotificationHubApplicationEventModule } from './application-event/application-event.module';
import { NotificationHubDeliveryChannelModule } from './delivery-channel/delivery-channel.module';
import { NotificationHubUserChannelConfigurationModule } from './user-channel-configuration/user-channel-configuration.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        NotificationHubLdapUserModule,
        NotificationHubEventModule,
        NotificationHubEventAttachModule,
        NotificationHubEventDeliveryStatusModule,
        NotificationHubSystemEventModule,
        NotificationHubApplicationEventModule,
        NotificationHubDeliveryChannelModule,
        NotificationHubUserChannelConfigurationModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationHubEntityModule {}
