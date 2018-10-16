export interface IEventAttach {
    id?: string;
    fileContentType?: string;
    file?: any;
    eventId?: string;
}

export class EventAttach implements IEventAttach {
    constructor(public id?: string, public fileContentType?: string, public file?: any, public eventId?: string) {}
}
