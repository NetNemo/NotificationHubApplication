/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NotificationHubTestModule } from '../../../test.module';
import { EventAttachDeleteDialogComponent } from 'app/entities/event-attach/event-attach-delete-dialog.component';
import { EventAttachService } from 'app/entities/event-attach/event-attach.service';

describe('Component Tests', () => {
    describe('EventAttach Management Delete Component', () => {
        let comp: EventAttachDeleteDialogComponent;
        let fixture: ComponentFixture<EventAttachDeleteDialogComponent>;
        let service: EventAttachService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [EventAttachDeleteDialogComponent]
            })
                .overrideTemplate(EventAttachDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EventAttachDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EventAttachService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete('123');
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith('123');
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
