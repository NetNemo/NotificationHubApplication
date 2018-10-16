import { IEvent } from 'app/shared/model//event.model';

export interface ISystemEvent {
    id?: string;
    description?: string;
    events?: IEvent[];
    userChannelConfigurationId?: string;
}

export class SystemEvent implements ISystemEvent {
    constructor(public id?: string, public description?: string, public events?: IEvent[], public userChannelConfigurationId?: string) {}
}
