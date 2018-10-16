import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserChannelConfiguration } from 'app/shared/model/user-channel-configuration.model';
import { UserChannelConfigurationService } from './user-channel-configuration.service';

@Component({
    selector: 'jhi-user-channel-configuration-delete-dialog',
    templateUrl: './user-channel-configuration-delete-dialog.component.html'
})
export class UserChannelConfigurationDeleteDialogComponent {
    userChannelConfiguration: IUserChannelConfiguration;

    constructor(
        private userChannelConfigurationService: UserChannelConfigurationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.userChannelConfigurationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'userChannelConfigurationListModification',
                content: 'Deleted an userChannelConfiguration'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-channel-configuration-delete-popup',
    template: ''
})
export class UserChannelConfigurationDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ userChannelConfiguration }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UserChannelConfigurationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.userChannelConfiguration = userChannelConfiguration;
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
