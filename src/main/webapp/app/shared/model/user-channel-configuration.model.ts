import { IApplicationEvent } from 'app/shared/model//application-event.model';
import { ISystemEvent } from 'app/shared/model//system-event.model';
import { ILdapUser } from 'app/shared/model//ldap-user.model';

export interface IUserChannelConfiguration {
    id?: string;
    mail?: string;
    slackToken1?: string;
    slackToken2?: string;
    slackToken3?: string;
    applicationEvents?: IApplicationEvent[];
    systemEvents?: ISystemEvent[];
    userIDS?: ILdapUser[];
    eventDeliveryStatusId?: string;
    deliveryChannelId?: string;
}

export class UserChannelConfiguration implements IUserChannelConfiguration {
    constructor(
        public id?: string,
        public mail?: string,
        public slackToken1?: string,
        public slackToken2?: string,
        public slackToken3?: string,
        public applicationEvents?: IApplicationEvent[],
        public systemEvents?: ISystemEvent[],
        public userIDS?: ILdapUser[],
        public eventDeliveryStatusId?: string,
        public deliveryChannelId?: string
    ) {}
}
