import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEventDeliveryStatus } from 'app/shared/model/event-delivery-status.model';
import { EventDeliveryStatusService } from './event-delivery-status.service';

@Component({
    selector: 'jhi-event-delivery-status-delete-dialog',
    templateUrl: './event-delivery-status-delete-dialog.component.html'
})
export class EventDeliveryStatusDeleteDialogComponent {
    eventDeliveryStatus: IEventDeliveryStatus;

    constructor(
        private eventDeliveryStatusService: EventDeliveryStatusService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.eventDeliveryStatusService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'eventDeliveryStatusListModification',
                content: 'Deleted an eventDeliveryStatus'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-event-delivery-status-delete-popup',
    template: ''
})
export class EventDeliveryStatusDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ eventDeliveryStatus }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EventDeliveryStatusDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.eventDeliveryStatus = eventDeliveryStatus;
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
