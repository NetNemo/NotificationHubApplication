/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { SystemEventUpdateComponent } from 'app/entities/system-event/system-event-update.component';
import { SystemEventService } from 'app/entities/system-event/system-event.service';
import { SystemEvent } from 'app/shared/model/system-event.model';

describe('Component Tests', () => {
    describe('SystemEvent Management Update Component', () => {
        let comp: SystemEventUpdateComponent;
        let fixture: ComponentFixture<SystemEventUpdateComponent>;
        let service: SystemEventService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [SystemEventUpdateComponent]
            })
                .overrideTemplate(SystemEventUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SystemEventUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SystemEventService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SystemEvent('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.systemEvent = entity;
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
                    const entity = new SystemEvent();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.systemEvent = entity;
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
