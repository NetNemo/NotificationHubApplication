/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { UserChannelConfigurationUpdateComponent } from 'app/entities/user-channel-configuration/user-channel-configuration-update.component';
import { UserChannelConfigurationService } from 'app/entities/user-channel-configuration/user-channel-configuration.service';
import { UserChannelConfiguration } from 'app/shared/model/user-channel-configuration.model';

describe('Component Tests', () => {
    describe('UserChannelConfiguration Management Update Component', () => {
        let comp: UserChannelConfigurationUpdateComponent;
        let fixture: ComponentFixture<UserChannelConfigurationUpdateComponent>;
        let service: UserChannelConfigurationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [UserChannelConfigurationUpdateComponent]
            })
                .overrideTemplate(UserChannelConfigurationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UserChannelConfigurationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserChannelConfigurationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new UserChannelConfiguration('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.userChannelConfiguration = entity;
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
                    const entity = new UserChannelConfiguration();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.userChannelConfiguration = entity;
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
