import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEventAttach } from 'app/shared/model/event-attach.model';

type EntityResponseType = HttpResponse<IEventAttach>;
type EntityArrayResponseType = HttpResponse<IEventAttach[]>;

@Injectable({ providedIn: 'root' })
export class EventAttachService {
    private resourceUrl = SERVER_API_URL + 'api/event-attaches';

    constructor(private http: HttpClient) {}

    create(eventAttach: IEventAttach): Observable<EntityResponseType> {
        return this.http.post<IEventAttach>(this.resourceUrl, eventAttach, { observe: 'response' });
    }

    update(eventAttach: IEventAttach): Observable<EntityResponseType> {
        return this.http.put<IEventAttach>(this.resourceUrl, eventAttach, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IEventAttach>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEventAttach[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
