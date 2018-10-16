import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEventAttach } from 'app/shared/model/event-attach.model';
import { EventAttachService } from './event-attach.service';

@Component({
    selector: 'jhi-event-attach-delete-dialog',
    templateUrl: './event-attach-delete-dialog.component.html'
})
export class EventAttachDeleteDialogComponent {
    eventAttach: IEventAttach;

    constructor(
        private eventAttachService: EventAttachService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.eventAttachService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'eventAttachListModification',
                content: 'Deleted an eventAttach'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-event-attach-delete-popup',
    template: ''
})
export class EventAttachDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ eventAttach }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EventAttachDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.eventAttach = eventAttach;
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
