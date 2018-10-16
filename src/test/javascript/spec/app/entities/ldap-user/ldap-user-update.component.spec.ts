/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { LdapUserUpdateComponent } from 'app/entities/ldap-user/ldap-user-update.component';
import { LdapUserService } from 'app/entities/ldap-user/ldap-user.service';
import { LdapUser } from 'app/shared/model/ldap-user.model';

describe('Component Tests', () => {
    describe('LdapUser Management Update Component', () => {
        let comp: LdapUserUpdateComponent;
        let fixture: ComponentFixture<LdapUserUpdateComponent>;
        let service: LdapUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [LdapUserUpdateComponent]
            })
                .overrideTemplate(LdapUserUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LdapUserUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LdapUserService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new LdapUser('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.ldapUser = entity;
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
                    const entity = new LdapUser();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.ldapUser = entity;
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
