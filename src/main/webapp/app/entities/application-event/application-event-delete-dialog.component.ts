import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApplicationEvent } from 'app/shared/model/application-event.model';
import { ApplicationEventService } from './application-event.service';

@Component({
    selector: 'jhi-application-event-delete-dialog',
    templateUrl: './application-event-delete-dialog.component.html'
})
export class ApplicationEventDeleteDialogComponent {
    applicationEvent: IApplicationEvent;

    constructor(
        private applicationEventService: ApplicationEventService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.applicationEventService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'applicationEventListModification',
                content: 'Deleted an applicationEvent'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-application-event-delete-popup',
    template: ''
})
export class ApplicationEventDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ applicationEvent }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ApplicationEventDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.applicationEvent = applicationEvent;
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
