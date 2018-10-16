/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { EventAttachUpdateComponent } from 'app/entities/event-attach/event-attach-update.component';
import { EventAttachService } from 'app/entities/event-attach/event-attach.service';
import { EventAttach } from 'app/shared/model/event-attach.model';

describe('Component Tests', () => {
    describe('EventAttach Management Update Component', () => {
        let comp: EventAttachUpdateComponent;
        let fixture: ComponentFixture<EventAttachUpdateComponent>;
        let service: EventAttachService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [EventAttachUpdateComponent]
            })
                .overrideTemplate(EventAttachUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EventAttachUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EventAttachService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EventAttach('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.eventAttach = entity;
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
                    const entity = new EventAttach();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.eventAttach = entity;
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
