import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDeliveryChannel } from 'app/shared/model/delivery-channel.model';

type EntityResponseType = HttpResponse<IDeliveryChannel>;
type EntityArrayResponseType = HttpResponse<IDeliveryChannel[]>;

@Injectable({ providedIn: 'root' })
export class DeliveryChannelService {
    private resourceUrl = SERVER_API_URL + 'api/delivery-channels';

    constructor(private http: HttpClient) {}

    create(deliveryChannel: IDeliveryChannel): Observable<EntityResponseType> {
        return this.http.post<IDeliveryChannel>(this.resourceUrl, deliveryChannel, { observe: 'response' });
    }

    update(deliveryChannel: IDeliveryChannel): Observable<EntityResponseType> {
        return this.http.put<IDeliveryChannel>(this.resourceUrl, deliveryChannel, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IDeliveryChannel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDeliveryChannel[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
