import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUserChannelConfiguration } from 'app/shared/model/user-channel-configuration.model';

type EntityResponseType = HttpResponse<IUserChannelConfiguration>;
type EntityArrayResponseType = HttpResponse<IUserChannelConfiguration[]>;

@Injectable({ providedIn: 'root' })
export class UserChannelConfigurationService {
    private resourceUrl = SERVER_API_URL + 'api/user-channel-configurations';

    constructor(private http: HttpClient) {}

    create(userChannelConfiguration: IUserChannelConfiguration): Observable<EntityResponseType> {
        return this.http.post<IUserChannelConfiguration>(this.resourceUrl, userChannelConfiguration, { observe: 'response' });
    }

    update(userChannelConfiguration: IUserChannelConfiguration): Observable<EntityResponseType> {
        return this.http.put<IUserChannelConfiguration>(this.resourceUrl, userChannelConfiguration, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IUserChannelConfiguration>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IUserChannelConfiguration[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
