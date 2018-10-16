import { IUserChannelConfiguration } from 'app/shared/model//user-channel-configuration.model';

export const enum Channel {
    MAIL = 'MAIL',
    SLACK = 'SLACK'
}

export interface IDeliveryChannel {
    id?: string;
    type?: Channel;
    description?: string;
    userChannelConfigurations?: IUserChannelConfiguration[];
}

export class DeliveryChannel implements IDeliveryChannel {
    constructor(
        public id?: string,
        public type?: Channel,
        public description?: string,
        public userChannelConfigurations?: IUserChannelConfiguration[]
    ) {}
}
