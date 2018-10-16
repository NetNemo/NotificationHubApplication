/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NotificationHubTestModule } from '../../../test.module';
import { EventDeliveryStatusDeleteDialogComponent } from 'app/entities/event-delivery-status/event-delivery-status-delete-dialog.component';
import { EventDeliveryStatusService } from 'app/entities/event-delivery-status/event-delivery-status.service';

describe('Component Tests', () => {
    describe('EventDeliveryStatus Management Delete Component', () => {
        let comp: EventDeliveryStatusDeleteDialogComponent;
        let fixture: ComponentFixture<EventDeliveryStatusDeleteDialogComponent>;
        let service: EventDeliveryStatusService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [EventDeliveryStatusDeleteDialogComponent]
            })
                .overrideTemplate(EventDeliveryStatusDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EventDeliveryStatusDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EventDeliveryStatusService);
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
