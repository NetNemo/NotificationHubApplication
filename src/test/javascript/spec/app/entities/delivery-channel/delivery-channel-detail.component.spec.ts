/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { DeliveryChannelDetailComponent } from 'app/entities/delivery-channel/delivery-channel-detail.component';
import { DeliveryChannel } from 'app/shared/model/delivery-channel.model';

describe('Component Tests', () => {
    describe('DeliveryChannel Management Detail Component', () => {
        let comp: DeliveryChannelDetailComponent;
        let fixture: ComponentFixture<DeliveryChannelDetailComponent>;
        const route = ({ data: of({ deliveryChannel: new DeliveryChannel('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [DeliveryChannelDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DeliveryChannelDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DeliveryChannelDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.deliveryChannel).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
