package net.vniia.dictionaries.readers;

import net.vniia.common.jpa.JPAQuery;
import net.vniia.common.jpa.JPAQueryFactory;
import net.vniia.common.reader.PageHelper;
import net.vniia.common.reader.PageQuery;
import net.vniia.common.reader.PageResponse;
import net.vniia.dictionaries.dto.CodeDirectionDto;
import net.vniia.dictionaries.entities.QCodeDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class CodeDirectionReader {
    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory = new JPAQueryFactory(() -> entityManager);

    @Autowired
    private PageHelper pageHelper;

    private static final QCodeDirection codeDirection = QCodeDirection.codeDirection;

    public PageResponse<CodeDirectionDto> getCodeDirection(PageQuery pageQuery) {
        JPAQuery<CodeDirectionDto> query = queryFactory
                .from(codeDirection)
                .selectDto(CodeDirectionDto.class)
                .orderBy(codeDirection.name.asc());
        return pageHelper.paginate(query, codeDirection, pageQuery);
    }
}
