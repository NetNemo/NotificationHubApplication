import { IEventAttach } from 'app/shared/model//event-attach.model';

export interface IEvent {
    id?: string;
    description?: string;
    text?: string;
    eventAttaches?: IEventAttach[];
    systemEventId?: string;
    applicationEventId?: string;
}

export class Event implements IEvent {
    constructor(
        public id?: string,
        public description?: string,
        public text?: string,
        public eventAttaches?: IEventAttach[],
        public systemEventId?: string,
        public applicationEventId?: string
    ) {}
}
