/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { EventDeliveryStatusDetailComponent } from 'app/entities/event-delivery-status/event-delivery-status-detail.component';
import { EventDeliveryStatus } from 'app/shared/model/event-delivery-status.model';

describe('Component Tests', () => {
    describe('EventDeliveryStatus Management Detail Component', () => {
        let comp: EventDeliveryStatusDetailComponent;
        let fixture: ComponentFixture<EventDeliveryStatusDetailComponent>;
        const route = ({ data: of({ eventDeliveryStatus: new EventDeliveryStatus('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [EventDeliveryStatusDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EventDeliveryStatusDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EventDeliveryStatusDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.eventDeliveryStatus).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
