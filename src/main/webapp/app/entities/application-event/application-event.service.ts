import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IApplicationEvent } from 'app/shared/model/application-event.model';

type EntityResponseType = HttpResponse<IApplicationEvent>;
type EntityArrayResponseType = HttpResponse<IApplicationEvent[]>;

@Injectable({ providedIn: 'root' })
export class ApplicationEventService {
    private resourceUrl = SERVER_API_URL + 'api/application-events';

    constructor(private http: HttpClient) {}

    create(applicationEvent: IApplicationEvent): Observable<EntityResponseType> {
        return this.http.post<IApplicationEvent>(this.resourceUrl, applicationEvent, { observe: 'response' });
    }

    update(applicationEvent: IApplicationEvent): Observable<EntityResponseType> {
        return this.http.put<IApplicationEvent>(this.resourceUrl, applicationEvent, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IApplicationEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IApplicationEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
