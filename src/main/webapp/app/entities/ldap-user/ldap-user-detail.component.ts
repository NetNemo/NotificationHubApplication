import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILdapUser } from 'app/shared/model/ldap-user.model';

@Component({
    selector: 'jhi-ldap-user-detail',
    templateUrl: './ldap-user-detail.component.html'
})
export class LdapUserDetailComponent implements OnInit {
    ldapUser: ILdapUser;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ldapUser }) => {
            this.ldapUser = ldapUser;
        });
    }

    previousState() {
        window.history.back();
    }
}
