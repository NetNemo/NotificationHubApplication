/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NotificationHubTestModule } from '../../../test.module';
import { SystemEventDeleteDialogComponent } from 'app/entities/system-event/system-event-delete-dialog.component';
import { SystemEventService } from 'app/entities/system-event/system-event.service';

describe('Component Tests', () => {
    describe('SystemEvent Management Delete Component', () => {
        let comp: SystemEventDeleteDialogComponent;
        let fixture: ComponentFixture<SystemEventDeleteDialogComponent>;
        let service: SystemEventService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [SystemEventDeleteDialogComponent]
            })
                .overrideTemplate(SystemEventDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SystemEventDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SystemEventService);
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
