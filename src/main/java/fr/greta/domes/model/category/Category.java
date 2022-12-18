package fr.greta.domes.model.category;

public enum Category {
    ALL() {
        @Override
        public String toString() {
            return "ALL";
        }
    },
    DOG() {
        @Override
        public String toString() {
            return "DOG";
        }
    },
    BIRD() {
        @Override
        public String toString() {
            return "BIRD";
        }
    },
    REPTILE() {
        @Override
        public String toString() {
            return "REPTILE";
        }
    },
    CAT() {
        @Override
        public String toString() {
            return "CAT";
        }
    },
    FISH() {
        @Override
        public String toString() {
            return "FISH";
        }
    }
}
