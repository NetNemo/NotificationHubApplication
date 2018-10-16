import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ILdapUser } from 'app/shared/model/ldap-user.model';
import { LdapUserService } from './ldap-user.service';
import { IUserChannelConfiguration } from 'app/shared/model/user-channel-configuration.model';
import { UserChannelConfigurationService } from 'app/entities/user-channel-configuration';

@Component({
    selector: 'jhi-ldap-user-update',
    templateUrl: './ldap-user-update.component.html'
})
export class LdapUserUpdateComponent implements OnInit {
    ldapUser: ILdapUser;
    isSaving: boolean;

    userchannelconfigurations: IUserChannelConfiguration[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private ldapUserService: LdapUserService,
        private userChannelConfigurationService: UserChannelConfigurationService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ldapUser }) => {
            this.ldapUser = ldapUser;
        });
        this.userChannelConfigurationService.query().subscribe(
            (res: HttpResponse<IUserChannelConfiguration[]>) => {
                this.userchannelconfigurations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.ldapUser.id !== undefined) {
            this.subscribeToSaveResponse(this.ldapUserService.update(this.ldapUser));
        } else {
            this.subscribeToSaveResponse(this.ldapUserService.create(this.ldapUser));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILdapUser>>) {
        result.subscribe((res: HttpResponse<ILdapUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUserChannelConfigurationById(index: number, item: IUserChannelConfiguration) {
        return item.id;
    }
}
