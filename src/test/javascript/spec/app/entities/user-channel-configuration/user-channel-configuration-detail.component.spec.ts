/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { UserChannelConfigurationDetailComponent } from 'app/entities/user-channel-configuration/user-channel-configuration-detail.component';
import { UserChannelConfiguration } from 'app/shared/model/user-channel-configuration.model';

describe('Component Tests', () => {
    describe('UserChannelConfiguration Management Detail Component', () => {
        let comp: UserChannelConfigurationDetailComponent;
        let fixture: ComponentFixture<UserChannelConfigurationDetailComponent>;
        const route = ({ data: of({ userChannelConfiguration: new UserChannelConfiguration('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [UserChannelConfigurationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UserChannelConfigurationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UserChannelConfigurationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.userChannelConfiguration).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
