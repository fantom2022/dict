package net.vniia.dictionaries.dto.composition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositionElementDto {
      private ProductUnitDto productUnitRoot;
      private ProductUnitDto productUnitNode;
      private ProductUnitDto productUnit;
      private WorkShopDto workShop;
      private RouteMapDto routeMap;

      private Long workShopNodeId; //cehIdNode; //Ид. цеха узла

      private String path; //node; // Полный путь до узла ветки в дереве (последовательность ид. ДСЕ)
      private String fullPath; //detail; //Полный путь ветки в дереве (последовательность ид. ДСЕ)

      private String specificationInventoryNumber; //insp; //Инвентарный номер СП
      private Long specificationSection; //razdelSp; //Раздел СП
      private Long specificationPosition; //nPoz; //Номер позиции СП
      private String compositionMode; //rezhimIsp; //Режим использования (из состава)
      private Long compositionLevel; //inLevel; //Уровень в дереве
      private String compositionCategory; //kategoriyaVh; //Категория (из состава)
      private String compositionReplaceType; //prPodboraZameny; //Признак подбора-замены (из состава)
      private Long compositionChangeFlag; //ppd; //Признак переменных данных ППД (из состава)
      private Long specificationCount; //kol; //Количество по СП
      private Long compositionNodeCount; //fullKol; //Количество по ветке
      private Long productUnitAlbumId; //komplIzdId; //Ид. комплекта, к которому относится ДСЕ (ветка)
      private Long dummyNodeFlag; //upe2; //Признак фиктивной ветки
      private Long albumFlag; //upeDse; //Признак УПЕ (комплекта)
      private Long compositionDeleteType; //przDel; //Признак удаления из состава
      private Long nodeCountByProductUnit; //fullKol1; //Количество по ветке на 1 изделие
      private Long productUnitType; //typeId; //Тип ДСЕ
      private Long replaceGroupId; //gruppaZameny; //Группа замены (из состава КТИ)
      private Long childReplaceGroupId; //podgruppaZameny; //Подгруппа замены (из состава КТИ)
      private String provisionType; //zagot; //Признак заготовки (из состава КТИ)

}
