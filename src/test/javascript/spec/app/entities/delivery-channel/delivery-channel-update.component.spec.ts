/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { DeliveryChannelUpdateComponent } from 'app/entities/delivery-channel/delivery-channel-update.component';
import { DeliveryChannelService } from 'app/entities/delivery-channel/delivery-channel.service';
import { DeliveryChannel } from 'app/shared/model/delivery-channel.model';

describe('Component Tests', () => {
    describe('DeliveryChannel Management Update Component', () => {
        let comp: DeliveryChannelUpdateComponent;
        let fixture: ComponentFixture<DeliveryChannelUpdateComponent>;
        let service: DeliveryChannelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [DeliveryChannelUpdateComponent]
            })
                .overrideTemplate(DeliveryChannelUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DeliveryChannelUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DeliveryChannelService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DeliveryChannel('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.deliveryChannel = entity;
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
                    const entity = new DeliveryChannel();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.deliveryChannel = entity;
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
