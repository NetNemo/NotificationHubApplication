import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISystemEvent } from 'app/shared/model/system-event.model';

type EntityResponseType = HttpResponse<ISystemEvent>;
type EntityArrayResponseType = HttpResponse<ISystemEvent[]>;

@Injectable({ providedIn: 'root' })
export class SystemEventService {
    private resourceUrl = SERVER_API_URL + 'api/system-events';

    constructor(private http: HttpClient) {}

    create(systemEvent: ISystemEvent): Observable<EntityResponseType> {
        return this.http.post<ISystemEvent>(this.resourceUrl, systemEvent, { observe: 'response' });
    }

    update(systemEvent: ISystemEvent): Observable<EntityResponseType> {
        return this.http.put<ISystemEvent>(this.resourceUrl, systemEvent, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<ISystemEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISystemEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
