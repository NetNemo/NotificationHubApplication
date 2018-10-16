/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { EventDeliveryStatusUpdateComponent } from 'app/entities/event-delivery-status/event-delivery-status-update.component';
import { EventDeliveryStatusService } from 'app/entities/event-delivery-status/event-delivery-status.service';
import { EventDeliveryStatus } from 'app/shared/model/event-delivery-status.model';

describe('Component Tests', () => {
    describe('EventDeliveryStatus Management Update Component', () => {
        let comp: EventDeliveryStatusUpdateComponent;
        let fixture: ComponentFixture<EventDeliveryStatusUpdateComponent>;
        let service: EventDeliveryStatusService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [EventDeliveryStatusUpdateComponent]
            })
                .overrideTemplate(EventDeliveryStatusUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EventDeliveryStatusUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EventDeliveryStatusService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EventDeliveryStatus('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.eventDeliveryStatus = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EventDeliveryStatus();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.eventDeliveryStatus = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
