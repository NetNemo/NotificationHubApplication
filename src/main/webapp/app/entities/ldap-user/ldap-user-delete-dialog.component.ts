import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILdapUser } from 'app/shared/model/ldap-user.model';
import { LdapUserService } from './ldap-user.service';

@Component({
    selector: 'jhi-ldap-user-delete-dialog',
    templateUrl: './ldap-user-delete-dialog.component.html'
})
export class LdapUserDeleteDialogComponent {
    ldapUser: ILdapUser;

    constructor(private ldapUserService: LdapUserService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.ldapUserService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'ldapUserListModification',
                content: 'Deleted an ldapUser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ldap-user-delete-popup',
    template: ''
})
export class LdapUserDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ldapUser }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LdapUserDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.ldapUser = ldapUser;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
