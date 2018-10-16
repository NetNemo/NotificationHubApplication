import { IEvent } from 'app/shared/model//event.model';

export interface IApplicationEvent {
    id?: string;
    description?: string;
    events?: IEvent[];
    userChannelConfigurationId?: string;
}

export class ApplicationEvent implements IApplicationEvent {
    constructor(public id?: string, public description?: string, public events?: IEvent[], public userChannelConfigurationId?: string) {}
}
