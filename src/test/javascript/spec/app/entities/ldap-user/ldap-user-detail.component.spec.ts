/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { LdapUserDetailComponent } from 'app/entities/ldap-user/ldap-user-detail.component';
import { LdapUser } from 'app/shared/model/ldap-user.model';

describe('Component Tests', () => {
    describe('LdapUser Management Detail Component', () => {
        let comp: LdapUserDetailComponent;
        let fixture: ComponentFixture<LdapUserDetailComponent>;
        const route = ({ data: of({ ldapUser: new LdapUser('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [LdapUserDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LdapUserDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LdapUserDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.ldapUser).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
