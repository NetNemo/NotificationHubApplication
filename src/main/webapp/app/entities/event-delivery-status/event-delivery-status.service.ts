import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEventDeliveryStatus } from 'app/shared/model/event-delivery-status.model';

type EntityResponseType = HttpResponse<IEventDeliveryStatus>;
type EntityArrayResponseType = HttpResponse<IEventDeliveryStatus[]>;

@Injectable({ providedIn: 'root' })
export class EventDeliveryStatusService {
    private resourceUrl = SERVER_API_URL + 'api/event-delivery-statuses';

    constructor(private http: HttpClient) {}

    create(eventDeliveryStatus: IEventDeliveryStatus): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(eventDeliveryStatus);
        return this.http
            .post<IEventDeliveryStatus>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(eventDeliveryStatus: IEventDeliveryStatus): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(eventDeliveryStatus);
        return this.http
            .put<IEventDeliveryStatus>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http
            .get<IEventDeliveryStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEventDeliveryStatus[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(eventDeliveryStatus: IEventDeliveryStatus): IEventDeliveryStatus {
        const copy: IEventDeliveryStatus = Object.assign({}, eventDeliveryStatus, {
            date:
                eventDeliveryStatus.date != null && eventDeliveryStatus.date.isValid() ? eventDeliveryStatus.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((eventDeliveryStatus: IEventDeliveryStatus) => {
            eventDeliveryStatus.date = eventDeliveryStatus.date != null ? moment(eventDeliveryStatus.date) : null;
        });
        return res;
    }
}
