import { Moment } from 'moment';
import { IUserChannelConfiguration } from 'app/shared/model//user-channel-configuration.model';

export interface IEventDeliveryStatus {
    id?: string;
    date?: Moment;
    status?: string;
    userChannelConfigurations?: IUserChannelConfiguration[];
}

export class EventDeliveryStatus implements IEventDeliveryStatus {
    constructor(
        public id?: string,
        public date?: Moment,
        public status?: string,
        public userChannelConfigurations?: IUserChannelConfiguration[]
    ) {}
}
