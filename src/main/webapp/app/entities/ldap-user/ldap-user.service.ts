import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILdapUser } from 'app/shared/model/ldap-user.model';

type EntityResponseType = HttpResponse<ILdapUser>;
type EntityArrayResponseType = HttpResponse<ILdapUser[]>;

@Injectable({ providedIn: 'root' })
export class LdapUserService {
    private resourceUrl = SERVER_API_URL + 'api/ldap-users';

    constructor(private http: HttpClient) {}

    create(ldapUser: ILdapUser): Observable<EntityResponseType> {
        return this.http.post<ILdapUser>(this.resourceUrl, ldapUser, { observe: 'response' });
    }

    update(ldapUser: ILdapUser): Observable<EntityResponseType> {
        return this.http.put<ILdapUser>(this.resourceUrl, ldapUser, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<ILdapUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILdapUser[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
