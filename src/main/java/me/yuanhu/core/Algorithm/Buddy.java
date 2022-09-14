package me.yuanhu.core.Algorithm;

public class Buddy {
    // index from 1 start not 0
    private final int[] memory;
    private final int[] deepths;
    private final int deepth;
    public Buddy(int powerOfTwo) {
        this.deepth = powerOfTwo;
        this.memory = new int[(int) Math.pow(2, powerOfTwo + 1)];
        this.deepths = new int[(int) Math.pow(2, powerOfTwo + 1)];
        initMemory();
    }
    // init each value with each id's deepth
    private void initMemory() {
        for (int i = 0; i <= deepth; i++) {
            int start = (int) Math.pow(2, i);
            int end = (int) Math.pow(2, i + 1);
            for (int j = start; j < end; j++) {
                this.memory[j] = i;
                this.deepths[j] = i;
            }
        }
    }
    /**
     * @param d the d = Log2(Total/size) the size is the memory you want allocate
     */
    public int allocateNode(int d) throws Exception {
        int id = findNode(d);
        if (id < 0) {
            throw new Exception("error id: " + id);
        }
        memory[id] = deepth + 1;// set deepth +1 to make id unusable
        updateParentsAlloc(id);
        return id;
    }
    /**
     * @param id id is the return value of allocateNode
     */
    public int freeNode(int id) throws Exception {
        //这里漏了点东西，暂时注释
//        if (id < 0 || id > ) {
//            throw new Exception("error id: " + id);
//        }
        memory[id] = deepths[id];
        updateParentsFree(id);
        return id;
    }
    private int findNode(int d) {
        int id = 1; // start index
        int val = memory[id];
        if (d > deepth) {
            return -2; // -2 presents error input,d must in range [0,deepth]
        }
        if (val > d) {
            return -1; // -1 presents no enough memory sapce for allocate
        }
        while ((memory[id] < d || memory[id] > deepths[id]) && memory[id] <= d) {
            int left = id << 1;
            int right = left + 1;
            if (memory[left] < d) {
                id = left;
                continue;
            } else {
                if (memory[left] == d) {
                    if (memory[left] > deepths[left]) {
                        id = left;
                        continue;
                    } else {
                        id = left;
                        return id;
                    }
                }
            }
            if (memory[right] < d) {
                id = right;
                continue;
            } else {
                if (memory[right] == d) {
                    if (memory[right] > deepths[right]) {
                        id = right;
                        continue;
                    } else {
                        id = right;
                        return id;
                    }
                }
            }
        }
        return id;
    }
    private void updateParentsAlloc(final int id) {
        if (id == 1) {
            return;
        }
        int tmp = id;
        while (tmp > 1) {
            int parentId = tmp >>> 1;
            int buddy = tmp ^ 1; // this is the important step, this ^ operation get id's buddy
            int selfValue = memory[tmp];
            int buddyValue = memory[buddy];
            int min = selfValue < buddyValue ? selfValue : buddyValue;
            //set min to parent
            memory[parentId] = min;
            tmp = parentId;
        }
    }
    private void updateParentsFree(final int id) {
        if (id == 1) {
            return;
        }
        int tmp = id;
        while (tmp > 1) {
            int parentId = tmp >>> 1;
            int buddy = tmp ^ 1;
            int selfValue = memory[tmp];
            int buddyValue = memory[buddy];
            // if equal then -1 else set min
            if (selfValue == buddyValue) {
                memory[parentId] = selfValue - 1;
            } else {
                int min = selfValue < buddyValue ? selfValue : buddyValue;
                memory[parentId] = min;
            }
            tmp = parentId;
        }
    }
    public void printAllIdValue() {
        System.out.println("********************************");
        for (int i = 0; i <= deepth; i++) {
            int start = (int) Math.pow(2, i);
            int end = (int) Math.pow(2, i + 1);
            StringBuilder s = new StringBuilder();
            for (int j = start; j < end; j++) {
                s.append(memory[j] + " ");
            }
            System.out.println(s.toString());
        }
        System.out.println("********************************");
    }
}